SET exectype tez;

ratings = LOAD '/user/maria_dev/ml-100k/u.data' AS (userID:int, movieID:int, rating:int, ratingTime:int);

ratingsFilter = FILTER ratings BY rating==5;
grouped = GROUP ratingsFilter ALL;

ratingsCount = foreach grouped Generate COUNT(ratingsFilter.movieID);

DUMP ratingsCount;
