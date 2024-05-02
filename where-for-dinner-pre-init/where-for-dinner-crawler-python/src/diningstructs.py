class Establishment:
    def __init__(self, diningName: str, address: str, locality: str, region: str, postalCode: str, phoneNumber: str, reservationURL: str):
        self.diningName = diningName
        self.address = address
        self.locality = locality
        self.region = region
        self.postalCode = postalCode
        self.phoneNumber = phoneNumber
        self.reservationURL = reservationURL

class Availability:
    def __init__(self): 
        self.searchName = "" 
        self.diningName = ""
        self.address = ""
        self.locality = ""
        self.region = ""
        self.postalCode = ""
        self.phoneNumber = ""
        self.reservationURL = ""
        self.availabilityWindows = []

class AvailabilityWindow:
    def __init__(self, startTime: int, endTime: int): 
        self.startTime = startTime   
        self.endTime = endTime

class SearchCriteria:
    def __init__(self, name: str, startTime: int, endTime: int, diningTypes, diningNames, postalCode: str, radius: int):  
        self.name = name
        self.startTime = startTime
        self.endTime = endTime
        self.diningTypes = diningTypes
        self.diningNames = diningNames
        self.postalCode = postalCode
        self.radius = radius         