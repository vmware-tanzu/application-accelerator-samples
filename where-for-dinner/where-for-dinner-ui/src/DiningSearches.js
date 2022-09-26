import PropTypes from 'prop-types';
import DiningSearch from "./DiningSearch";

function DiningSearches(props)
{
    console.log("DiningSearches searches: " + props.submittedSearches);

    const searches = props.submittedSearches.map((search, index)=>{
        return <DiningSearch search={search} submittedSearches={props.submittedSearches} setSubmittedSearches={props.setSubmittedSearches}
               id={search.id}
               key={search.id}/>
    });

    return(
        
        <div name="diningSearches" id="diningSearches">
          <div className="bannerHeader"> 
            <div className="bannerCenter">
            <label className="searchesHeader">Submitted Searches And Results</label>
            </div>      	
          </div>           
          {searches}
        </div>
    );
}

DiningSearches.propTypes = {
    submittedSearches: PropTypes.array
  }

DiningSearches.defaultProps = {
    submittedSearches: [{
      name:"No Searched Submitted",
      id: -1
    }]
  }

export default DiningSearches;