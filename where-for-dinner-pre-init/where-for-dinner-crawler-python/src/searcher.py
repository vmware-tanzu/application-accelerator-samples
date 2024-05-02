from diningstructs import SearchCriteria
import abc


class Search(abc.ABC):

    @abc.abstractmethod
    def search(self, crit: SearchCriteria):
        pass