CREATE KEYSPACE crimes WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

use crimes;

CREATE TABLE crimes (
  "ID" int  ,
  "Case_Number" text  ,
  "Date" text  ,
  "Block" text  ,
  "IUCR" text  ,
  "Primary_Type" text  ,
  "Description" text  ,
  "Location_Description" text  ,
  "Arrest" text  ,
  "Domestic" text  ,
  "Beat" text  ,
  "District" text  ,
  "Ward" text  ,
  "Community_Area" text  ,
  "FBI_Code" text  ,
  "X_Coordinate" text  ,
  "Y_Coordinate" text  ,
  "Year" int  ,
  "Updated_On" text  ,
  "Latitude" text  ,
  "Longitude" text  ,
  "Location" text , 
  PRIMARY KEY ("ID")
) ;
