import axios from "axios";
import { useEffect, useRef, useState } from "react";
import Availability from "./Availability";
import delIco from "./images/delete.png";
import useCollapse from 'react-collapsed';

function DiningSearch(props)
{
    const [searchResults, setSearchResults] = useState([]);

    const deleteSearch = (e)=>
    {
       if (window.confirm("Do you wish do delete the search " + props.search.name))
       {
         axios.delete('/api/search/search/' + props.search.id)
         //axios.delete('http://where-for-dinner.perfect300rock.com/api/search/' + props.search.id)
         .then(rest =>{
            var newSearches = props.submittedSearches.filter(obj => obj.id != props.search.id);
            props.setSubmittedSearches(newSearches);
         })
         .catch(error => console.error(error));
       }
    }

    const loadSearchAvailability= () =>
    {
      axios.get('/api/availability/availability/' + props.search.name)
      //axios.get('http://where-for-dinner.perfect300rock.com/api/availability/' + props.search.name)
      .then(resp => 
      {
        if (resp.data.length > 0)
        {
          console.log("Returned searches: " + resp.data);
          return resp.data.map((result, index) =>{
            return <Availability avail={result}
                  id={result.searchName + result.diningName}
                  key={result.searchName + result.diningName}/>
          });
        }
      }
      )
      .then(avails => setSearchResults(avails))
      .catch(error => console.error(error));
    }

    useEffect(() => {
      loadSearchAvailability();
      const interval = setInterval(() => {
        loadSearchAvailability();
      }, 30000);
      return () => clearInterval(interval);
      },
       []); 

      const config = {
            duration: 500
      };
      const { getCollapseProps, getToggleProps, isExpanded } = useCollapse(config);
    

    return (
      <div>
        <p/>
        <label className="header" {...getToggleProps()}>
              {isExpanded ? '-' : '+'}
          </label>
          <label>{props.search.name} </label>

          {props.search.id !== -1 ? 
                    <img src={delIco} width={15} height={15} onClick={deleteSearch}/>	
            : null}
          <div {...getCollapseProps()}>
              {searchResults}
          </div>
      </div>
    )
}

export default DiningSearch;