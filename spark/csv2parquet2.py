#install pandas, pyarrow
import pandas as pd
df = pd.read_csv('/home/cosmin/Downloads/joaca/usa-cers-dataset/USA_cars_datasets.csv')
df.to_parquet('/home/cosmin/Downloads/joaca/usa-cers-dataset/output.parquet')
