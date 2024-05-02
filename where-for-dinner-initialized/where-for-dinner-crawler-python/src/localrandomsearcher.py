from diningstructs import Establishment, SearchCriteria, Availability, AvailabilityWindow
from searcher import Search
import random
import logging

staticDining = [];
staticDining.append(Establishment("Bristol", "51 E 14th St", "Kansas City", "MO", "64106", "(816) 448-6007", "https://bristolseafoodgrill.com/KansasCity/Reservations"))
staticDining.append(Establishment("Chophouse", "71 E 14th St", "Kansas City", "MO", "64106", "(816) 994-8800", "https://801chophouse.com/reservations/"))
staticDining.append(Establishment("Savoy Grill", "219 W 9th St", "Kansas City", "MO", "64105", "(816) 443-4260", "https://www.opentable.com/r/the-savoy-at-21c-kansas-city"))
staticDining.append(Establishment("Hereford House", "8661 N Stoddard Ave", "Kansas City", "MO", "64153", "(816) 584-9000", "https://www.herefordhouse.com/reservations"))
staticDining.append(Establishment("Jack Stack Barbecue | Freight House", "101 W 22nd St #300", "Kansas City", "MO", "64108", "(816) 472-7427", "https://www.jackstackbbq.com/reserve-a-table"))
staticDining.append(Establishment("The Capital Grille", "4760 Broadway Blvd", "Kansas City", "MO", "64112", "(816) 531-8345", "https://www.thecapitalgrille.com/reservations?setRestaurant=8064"))
staticDining.append(Establishment("Gram & Dun", "600 Ward Pkwy", "Kansas City", "MO", "64112", "(816) 389-2900", "https://gramanddun.com/reservations"))
staticDining.append(Establishment("Season 52", "340 Ward Pkwy", "Kansas City", "MO", "64112", "(816) 531-0052", "https://www.seasons52.com/reservations?setRestaurant=4523"))
staticDining.append(Establishment("Nick & Jakes Parkville", "6325 Lewis St #110", "Parkville", "MO", "64152", "(816) 584-8535", ""))
staticDining.append(Establishment("The Melting Pot", "450 Ward Pkwy", "Kansas City", "MO", "64112", "(816) 931-6358", "https://www.meltingpot.com/reservations.aspx?location=kansas-city-mo"))
staticDining.append(Establishment("Fogo de Chao", "222 W 47th St", "Kansas City", "MO", "64112", "(816) 931-7700", "https://www.opentable.com/r/fogo-de-chao-brazilian-steakhouse-kansas-city?ref=1068"))
staticDining.append(Establishment("Kona Grill", "444 Ward Pkwy", "Kansas City", "MO", "64112", "(816) 931-5888", "https://www.konagrill.com/reservations"))

HOUR = 3600 * 1000
	
HALF_HOUR = HOUR / 2;

class LocalRandomSearcher(Search):

    def search(self, crit: SearchCriteria):
        dining = createRandomDinings(crit) 

        retAvail = []

        for din in dining:
            retAvail.append(createRandomAvailability(din, crit))

        return retAvail
    
def createRandomDinings(crit: SearchCriteria):
    bucket = []
    numDinings = 0;
    dinings = []

    logging.info('Generating random dining options.')

    if crit.diningNames in (None, ''):
       
        numDinings = random.randint(1,len(staticDining))
        bucket = staticDining.copy()

    else:

        dinNames = crit.diningNames;
        filteredDining = []

        for est in staticDining:
            if isEstablishmentInList(est, dinNames):
                filteredDining.append(est)

        if len(filteredDining) == 0:
            return bucket
        
        numDinings = random.randint(1,len(filteredDining))
        bucket = filteredDining
        
    for i in range(0, numDinings):
        bucketEntryIdx = random.randint(0,len(bucket) -1)
        item = bucket[bucketEntryIdx]
        dinings.append(item)
        bucket.remove(item)

    logging.info("Generated %d random dining options.", len(dinings));

    return dinings

def createRandomAvailability(dining: Establishment, crit: SearchCriteria):
    
    isTimesAvailable = random.randint(0,10) > 1 and crit.endTime != None and crit.startTime != None
    
    avail = Availability()

    if isTimesAvailable:
        times = []

        windowSize = int(crit.endTime) - int(crit.startTime)

        if windowSize <= HOUR:
            times.append(int(crit.startTime))
            times.append(int(crit.endTime))
        else:
            max = int(crit.endTime) - HOUR
            min = int(crit.startTime)
            startTime = min + int(random.random() * (max - min))
            startTime = roundToHalfHour(startTime - HALF_HOUR);
    
            max = int(crit.endTime)
            min = startTime + HALF_HOUR;
            endTime = min + int(random.random() * (max - min));
            endTime = roundToHalfHour(endTime - HALF_HOUR);

            times.append(int(startTime))
            times.append(int(endTime))     

        avail.availabilityWindows.append(AvailabilityWindow(times[0], times[1]))    

    avail.searchName = crit.name
    avail.diningName = dining.diningName
    avail.address = dining.address
    avail.locality = dining.locality
    avail.phoneNumber = dining.phoneNumber
    avail.postalCode = dining.postalCode
    avail.region = dining.region
    avail.reservationURL = dining.reservationURL

    return avail

def isEstablishmentInList(est: Establishment, names: str):

    for name in names.split(","):
        if est.diningName.casefold() == name.casefold():
            return True
    
    return False

def roundToHalfHour(time: int):
    if time % HALF_HOUR == 0:
        return time;

    return (HALF_HOUR - (time % HALF_HOUR)) + time;