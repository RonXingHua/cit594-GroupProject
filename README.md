# CIT594-Project Report

## 1. Additional Feature

We implemented the feature for calculating total residential market value per capita for area (zip) which has the most parking violations for METER EXPIRED. As compared to feature No.5, instead of getting the zip code from user input, we chose the area which has the most parking violations for METER EXPIRED. The zip code can be retrieved from Parking Violations data set, and the rest of the calculation will be same as feature No.5. The purpose of this feature is to verify potential correlation between certain parking violation and total residential market value per capita.

Test: We used csv file to filter out the area with the most parking violations for METER EXPIRED. To be more specific, we included both "METER EXPIRED" and "METER EXPIRED CC" violations. And input the zip code in feature No.5 to make sure we are getting the same result for feature No.6.

## 2. Use of Data Structures

Data Structure: Map<Integer, Integer> \
Class: PropertyProcessor\
Method: getTtlMarketValuePerCapitaForMostMeterExpZip\
Object: meterExpByZip\
Justification: We used the Map here in order to store all the zip code and their respective number of "METER EXPIRED" parking violations so that we could further determine which area has the most "METER EXPIRED" parking violations. We also considered Set, but Set contains the same object once even if you add it multiple times unless for each set entry, a separated linkedlist is attached which makes it more complex than Map.


Data Structure: Map<Integer, String>\
Class: PVProcessor\
Method: getTtlFinesPerCapitaForZip\
Object: ttlFinesPerCapitaForZip\
Justification: We used the Map here in order to store all the zip code and their respective total Fines Per Capita so that we could further sort the result in ascending numerical order. We stored zip code and fine as a key-pair notation. For sorting purpose, all the keys would be pushed into a link and simple sort method would get the job done.


Data Structure: List<PV>\
Class: PVCSVReader\
Method: getAllPVs\
Object: parkingViolations\
Justification: We used List to store all the parkingViolations. For each PV instance, it contains time, fine, desciption, anonymousID, state, uniqueID and zip code. We thought about using Set and Map also, but in this case we didn't know whether they are duplicated entries or not so Set is not a good option. And we didn't have a requirement for storing a key-value pair for further computation, but a need of frequent search options. In the end, we chose ArrayList as the right data structure.



