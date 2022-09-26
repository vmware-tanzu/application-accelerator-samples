import DiningTypes from "./DiningTypes";
import DiningNames from "./DiningNames";
import { useRef, useState } from "react";
import axios from "axios";

function SearchDefForm(props)
{
  const [startTime,setStartTime] = useState(new Date())
  const [endTime,setEndTime] = useState(new Date())

  const searchNameRef = useRef(null);
  const zipCodeRef = useRef(null);
  const radiusRef = useRef(null);
  const diningNameRef = useRef(null);

  const handlStartDate = (e) => {
    setStartTime(new Date(e.target.value));
  };

  const handlEndDate = (e) => {
    setEndTime(new Date(e.target.value));
  };

  const validateInput = ()=>
  {
    // ensure the end date time is past now
    if (new Date() > endTime)
    {
      alert("The end time must be a future date and time.");
      return false;
    }

    if (props.submittedSearches)
    {
      // ensure that we don't have a duplicate search name
      for (const search of props.submittedSearches)
      {
        if (search.name === searchNameRef.current.value)
        {
          alert("A dining search with the name \""  + searchNameRef.current.value + "\" already exists");
          return false;
        }
      }
    }
    return true;
    
  };

  const handleSearchClick = (e)=>{
        e.preventDefault();

        if (!validateInput())
          return;

        var searchToSubmit = {
          diningNames : diningNameRef.current.value,
          postalCode: zipCodeRef.current.value,
          radius: radiusRef.current.value,
          name: searchNameRef.current.value,
          startTime: startTime.valueOf(),
          endTime: endTime.valueOf(),
          continousSearch: "true"
        };

        axios.put('/api/search/search', searchToSubmit)
        .then((resp)=> {
          var newSubmittedSearched =  props.submittedSearches ?  [...props.submittedSearches, resp.data] : [resp.data]; 
          props.setSubmittedSearches(newSubmittedSearched);
        })
        .catch(error => console.error(error))

      };

    return(
      
         <div id="submissionForm" name="submissionForm" >
            <div className="container welcomeContent">
              Use the form below to create and submit search, and search results will be displayed below the search form.  Click the "+" symbol to 
              expand the results of a search, and the "-" symbol to collapse the search results.  Once you are finished with a search, 
              you may delete the search and its results by clicking on the trashcan icon.
              <p/>
            </div>  

         <form onSubmit={handleSearchClick} name="inputForm" id="inputForm" className="submissionContainer">
            <header>Submit Search Information</header>
            <label id="searchNameLabel">Search Name</label>
            <input id="searchName" type="text" placeholder="Unique Search Name" ref={searchNameRef} required></input><br/>
            <label id="zipCodeLabel">Zip Code</label>
            <input id="zipCode" type="number" placeholder="Zip code" ref={zipCodeRef} required></input><br/>
            <label id="radiusLabel">Radius</label>
            <input id="radius" type="number" placeholder="Search radius in miles" ref={radiusRef}></input><br/>
            <label id="diningNameLabel">Restaurant Names:</label>
            <input id="diningName" type="text" placeholder="Restaurant Names (seperated by a comma)" ref={diningNameRef}></input><br/>                 
            <label id="startTimeLabel">Start Time</label>
            <input id="startTime" type="datetime-local" onChange={handlStartDate} value={startTime.toISOString().substring(0,16)} required></input><br/>   
            <label id="endTimeLabel">End Time</label>
            <input id="endTime" type="datetime-local" onChange={handlEndDate} value={endTime.toISOString().substring(0,16)} required></input>   
            <div align="center" id="submitSearch">
            <button>Submit Search</button>
            </div>
         </form>
         </div>
    );
}

export default SearchDefForm;