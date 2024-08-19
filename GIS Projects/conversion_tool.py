import arcpy
import math

input_file = arcpy.GetParameterAsText(0)
project = arcpy.GetParameterAsText(1)
station_code = arcpy.GetParameterAsText(2)
level = arcpy.GetParameterAsText(3)
output_folder = arcpy.GetParameterAsText(4)

# Scales geometry by a given scale factor towards the origin
def scale_geom(geom, scale):
    if geom is None: return None

    # sets reference point at 0,0
    refgeom = arcpy.PointGeometry(arcpy.Point(0,0))
    newparts = []
    for pind in range(geom.partCount):
        part = geom.getPart(pind)
        newpart = []
        for ptind in range(part.count):
            apnt = part.getObject(ptind)
            if apnt is None:
                # polygon boundaries and holes are all returned in the same part.
                # A null point separates each ring, so just pass it on to
                # preserve the holes.
                newpart.append(apnt)
                continue
            bdist = refgeom.distanceTo(apnt)

            bpnt = arcpy.Point(bdist, 0)
            adist = refgeom.distanceTo(bpnt)
            cdist = arcpy.PointGeometry(apnt).distanceTo(bpnt)

            # Law of Cosines, angle of C given lengths of a, b and c
            angle = math.acos((adist**2 + bdist**2 - cdist**2) / (2 * adist * bdist))

            scaledist = bdist * scale

            # If the point is below the reference point then our angle
            # is actually negative
            if apnt.Y < 0: angle = angle * -1

            # Create a new point that is scaledist from the origin
            # along the x axis. Rotate that point the same amount
            # as the original then translate it to the reference point
            scalex = scaledist * math.cos(angle)
            scaley = scaledist * math.sin(angle)

            newpart.append(arcpy.Point(scalex, scaley))
        newparts.append(newpart)

    if newparts != []:
        return arcpy.Geometry(geom.type, arcpy.Array(newparts), geom.spatialReference)

# Shifts the geometry a certain amount in the x and y directions
def shift_geom(geom, shift_x, shift_y):
    if geom is None: return None
    
    newparts = []
    for pind in range(geom.partCount):
        part = geom.getPart(pind)
        newpart = []
        for ptind in range(part.count):
            apnt = part.getObject(ptind)
            if apnt is None:
                # Preserve null points (for holes)
                newpart.append(apnt)
                continue
            
            # Create a new point with the shift applied
            shifted_point = arcpy.Point(apnt.X - shift_x, apnt.Y - shift_y)
            newpart.append(shifted_point)
        
        newparts.append(newpart)

    if newparts:
        return arcpy.Geometry(geom.type, arcpy.Array(newparts), geom.spatialReference)

def EditLayers(layer):

    shift_factor = 0
    scale_factor = 1

    # Different georeferencing factors for different projects
    if project == "North":
        shift_factor = 300000
        scale_factor = 0.999965007
    if project == "South":
        shift_factor = 100000
        scale_factor = 0.999997515
    if project == "Central":
        shift_factor = 200000
        scale_factor = 0.999979745
    if project == "East":
        shift_factor = 328083.3333
        scale_factor = 0.999976415
    if project == "Tacoma":
        shift_factor = 100000
        scale_factor = 0.999978298
    if project == "Lynnwood":
        shift_factor = 100000
        scale_factor = 0.999947054

    with arcpy.da.UpdateCursor(layer, ["SHAPE@"]) as cursor:
        for row in cursor:
            # Get the geometry
            geometry = row[0]

            # Scale the geometry towards the origin
            if geometry.type == 'point':
                # Check if inches-to-feet scaling is necessary
                if (geometry.centroid.X / 12) > shift_factor or (geometry.centroid.Y / 12) > shift_factor:
                    if geometry.centroid.X > 10000000:
                        scaled_x = geometry.centroid.X * (1/12.0)
                        scaled_y = geometry.centroid.Y * (1/12.0)
                        shifted_x = scaled_x - shift_factor
                        shifted_y = scaled_y - shift_factor
                        scaled_x = shifted_x * scale_factor
                        scaled_y = shifted_y * scale_factor
                        scaled_geometry = arcpy.Point(scaled_x, scaled_y)
                    else:
                        shifted_x = geometry.centroid.X - shift_factor
                        shifted_y = geometry.centroid.Y - shift_factor
                        scaled_x = shifted_x * scale_factor
                        scaled_y = shifted_y * scale_factor
                        scaled_geometry = arcpy.Point(scaled_x, scaled_y)
                else:
                    scaled_geometry = arcpy.Point(geometry.centroid.X, geometry.centroid.Y)
            else:
                if geometry is None:
                    scaled_geometry = None
                else:
                    if geometry.centroid.X > 10000000:
                        scaled_geometry = scale_geom(geometry, (1/12.0))
                        shifted_geometry = shift_geom(scaled_geometry, shift_factor, shift_factor)
                        scaled_geometry = scale_geom(shifted_geometry, scale_factor)
                    else:
                        shifted_geometry = shift_geom(geometry, shift_factor, shift_factor)
                        scaled_geometry = scale_geom(shifted_geometry, scale_factor)
            # Update the geometry in the feature layer
            row[0] = scaled_geometry
            cursor.updateRow(row)
    return layer

def FieldCalc(layer):  # Field Calculations

    # To allow overwriting outputs change overwriteOutput option to True.
    arcpy.env.overwriteOutput = True

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="FACILITY_CODE", field_type="TEXT", field_precision=None, field_scale=None, field_length=10, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="DXF_LAYER", field_type="TEXT", field_precision=None, field_scale=None, field_length=150, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="CONVERSION_DATE", field_type="DATE", field_precision=None, field_scale=None, field_length=None, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="P_ASSETNUM", field_type="TEXT", field_precision=None, field_scale=None, field_length=25, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="ASSETNUM", field_type="TEXT", field_precision=None, field_scale=None, field_length=25, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table= layer, field_name="ASSETCLASS", field_type="TEXT", field_precision=None, field_scale=None, field_length=75, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]

    # Process: Add Field (Add Field) (management)
    layer = arcpy.management.AddField(in_table=layer, field_name="LEVEL_NUM", field_type="DOUBLE", field_precision=None, field_scale=None, field_length=None, field_alias="", field_is_nullable="NULLABLE", field_is_required="NON_REQUIRED", field_domain="")[0]


    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="Layer_Code", expression="ExtractDSM(!Layer!)", expression_type="PYTHON3", code_block="""def ExtractDSM(s):
    arr = s.split(\"-\")
    if len(arr) > 1:
        return arr[1]
    else:
        return s """, field_type="TEXT")[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="FACILITY_CODE", expression=f'"{station_code}"', expression_type="PYTHON3", code_block="")[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="CONVERSION_DATE", expression="DateNow()", expression_type="PYTHON3", code_block="""from datetime import date
def DateNow():
    return date.today().strftime("%m/%d/%y")""")[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="LEVEL_NUM", expression=int(level), expression_type="PYTHON3", code_block="")[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="DXF_LAYER", expression="!DocName!", expression_type="PYTHON3", code_block="")[0]

    # Process: Alter Field (Alter Field) (management)
    layer = arcpy.management.AlterField(in_table=layer, field="OBJECTID", new_field_name="", new_field_alias="XNUM", field_type="", field_length=4, field_is_nullable="NON_NULLABLE", clear_field_alias="DO_NOT_CLEAR")[0]

    # Process: Join Field (Join Field) (management)
    layer = arcpy.management.JoinField(in_data=layer, in_field="FACILITY_CODE", join_table="Station_codes", join_field="Station_code", fields=["FACILITY_NAME"])[0]

    # Process: Join Field (Join Field) (management)
    layer = arcpy.management.JoinField(in_data=layer, in_field="Layer_Code", join_table="Station_dsm", join_field="Layer_Code", fields=["DXF_FEATURE_TYPE", "ASSET_CODE", "ASSETTYPE"])[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="StationId", expression="MakeStationId(!FACILITY_CODE!, str(int(!LEVEL_NUM!)))", expression_type="PYTHON3", code_block="""def MakeStationId(x, y):
    return x + "_" + y""", field_type="TEXT")[0]

    # Process: Join Field (Join Field) (management)
    layer = arcpy.management.JoinField(in_data=layer, in_field="StationId", join_table="Level_Names", join_field="StationId", fields=["LEVEL"])[0]

    # Process: Calculate Field (Calculate Field) (management)
    layer = arcpy.management.CalculateField(in_table=layer, field="P_ASSETNUM", expression="MakeParentId(!FACILITY_CODE!,!ASSET_CODE!)", expression_type="PYTHON3", code_block="""def MakeParentId(x, y):
    if x != None and y != None:
        return x + "-" + y
    else:
        return None""")[0]

    return layer

def Model():  # Model

    # To allow overwriting outputs change overwriteOutput option to True.
    arcpy.env.overwriteOutput = True

    arcpy.AddMessage("Converting Files...")
        
    GIS_Testing_gdb = output_folder

    if project == "Tacoma":
        temp = arcpy.conversion.CADToGeodatabase(input_cad_datasets=[input_file], out_gdb_path=GIS_Testing_gdb, out_dataset_name="temp", reference_scale=1000, spatial_reference="PROJCS['NAD_1983_HARN_StatePlane_Washington_South_FIPS_4602_Feet',GEOGCS['GCS_North_American_1983_HARN',DATUM['D_North_American_1983_HARN',SPHEROID['GRS_1980',6378137.0,298.257222101]],PRIMEM['Greenwich',0.0],UNIT['Degree',0.0174532925199433]],PROJECTION['Lambert_Conformal_Conic'],PARAMETER['False_Easting',1640416.666666667],PARAMETER['False_Northing',0.0],PARAMETER['Central_Meridian',-120.5],PARAMETER['Standard_Parallel_1',45.83333333333334],PARAMETER['Standard_Parallel_2',47.33333333333334],PARAMETER['Latitude_Of_Origin',45.33333333333334],UNIT['Foot_US',0.3048006096012192]];-117498300 -98850300 3048.00609601219;-100000 10000;-100000 10000;3.28083333333333E-03;0.001;0.001;IsHighPrecision")[0]
    else:
        temp = arcpy.conversion.CADToGeodatabase(input_cad_datasets=[input_file], out_gdb_path=GIS_Testing_gdb, out_dataset_name="temp", reference_scale=1000, spatial_reference="PROJCS['NAD_1983_HARN_StatePlane_Washington_North_FIPS_4601_Feet',GEOGCS['GCS_North_American_1983_HARN',DATUM['D_North_American_1983_HARN',SPHEROID['GRS_1980',6378137.0,298.257222101]],PRIMEM['Greenwich',0.0],UNIT['Degree',0.0174532925199433]],PROJECTION['Lambert_Conformal_Conic'],PARAMETER['False_Easting',1640416.666666667],PARAMETER['False_Northing',0.0],PARAMETER['Central_Meridian',-120.8333333333333],PARAMETER['Standard_Parallel_1',47.5],PARAMETER['Standard_Parallel_2',48.73333333333333],PARAMETER['Latitude_Of_Origin',47.0],UNIT['Foot_US',0.3048006096012192]];-117104300 -99539600 3048.00609601219;-100000 10000;-100000 10000;3.28083333333333E-03;0.001;0.001;IsHighPrecision")[0]

    
    Point = temp + "\\Point"
    Annotation = temp + "\\Annotation"
    Polygon = temp + "\\Polygon"
    Polyline = temp + "\\Polyline"
                            
    # Process: Feature Class to Feature Class (Feature Class to Feature Class) (conversion)
    dxf_point = arcpy.conversion.FeatureClassToFeatureClass(in_features=Point, out_path=GIS_Testing_gdb, out_name="dxf_point_" + station_code + "_" + level, where_clause="", config_keyword="")[0]

    # Process: Feature Class to Feature Class (2) (Feature Class to Feature Class) (conversion)
    dxf_annotation = arcpy.conversion.FeatureClassToFeatureClass(in_features=Annotation, out_path=GIS_Testing_gdb, out_name="dxf_annotation_" + station_code + "_" + level, where_clause="", config_keyword="")[0]
       
    # Process: Feature Class to Feature Class (4) (Feature Class to Feature Class) (conversion)
    dxf_polygon = arcpy.conversion.FeatureClassToFeatureClass(in_features=Polygon, out_path=GIS_Testing_gdb, out_name="dxf_polygon_" + station_code + "_" + level, where_clause="", config_keyword="")[0]

    # Process: Feature Class to Feature Class (5) (Feature Class to Feature Class) (conversion)
    dxf_polyline = arcpy.conversion.FeatureClassToFeatureClass(in_features=Polyline, out_path=GIS_Testing_gdb, out_name="dxf_polyline_" + station_code + "_" + level, where_clause="", config_keyword="")[0]

    arcpy.Delete_management(temp,"")
        
    arcpy.AddMessage("Georeferencing Features...")

    arcpy.AddMessage("Point layer:")
    dxf_point = EditLayers(dxf_point)
        
    arcpy.AddMessage("Annotation layer:")
    dxf_annotation = EditLayers(dxf_annotation)
        
    arcpy.AddMessage("Polygon layer:")
    dxf_polygon = EditLayers(dxf_polygon)
        
    arcpy.AddMessage("Polyline layer:")
    dxf_polyline = EditLayers(dxf_polyline)

    arcpy.AddMessage("Caclulating Fields...")

    arcpy.AddMessage("Point layer:")
    dxf_point = FieldCalc(dxf_point)
        
    arcpy.AddMessage("Annotation layer:")
    dxf_annotation = FieldCalc(dxf_annotation) 
        
    arcpy.AddMessage("Polygon layer:")
    dxf_polygon = FieldCalc(dxf_polygon)
        
    arcpy.AddMessage("Polyline layer:")
    dxf_polyline = FieldCalc(dxf_polyline)

    arcpy.AddMessage("Complete!")

if __name__ == '__main__':
    Model()

