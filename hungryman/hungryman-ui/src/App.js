import { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import DiningSearches from './DiningSearches'
import SearchDefForm from './SearchDefForm'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';

function App() {

  const [submittedSearches, setSubmittedSearches] = useState();

  const loadSearches = () => {
    axios.get('/api/search/search')
    .then(resp => setSubmittedSearches(resp.data))
    .catch(error => console.error(error))
  };

  useEffect(() => {
    loadSearches();
    const interval = setInterval(() => {
      loadSearches();
    }, 60000);
    return () => clearInterval(interval);

  }, []); 

  const login = () => {
    let port = (window.location.port ? ':' + window.location.port : '');
    window.location.href = '//' + window.location.hostname + port + '/diningsearch';
  }


  return (
//    Future Security Work	
//    <Router>
//      <div align="left"  className="App">
//            <div className="bannerHeader"> 
//              <div className="bannerCenter">
//              <label className="bannerLabel">Hungryman Dining Availability Search</label>
//              </div>      	
//            </div>
//            <div className="container welcomeContent">
//                      The Hungryman Dining Availability application continously searches for dining availability based on desired search
//                      parameters.  Once a search is submitted, the Hugryman system will continously scour different sources for dining
//                      availability that match your preferences and display dining availability in near real time.  Searches will continue even 
//                      after you leave the application, so you may return a later time to check to see if any new availability has been found.
//            </div>          
//            <Switch>
//              <Route path="/home">
//                <div align="center" id="login">
//                  <button onClick={login}>Login</button>
//                </div>
//              </Route>
//              <Route path="/diningsearch">
//              <form action="/scg-logout" method="POST" id="form">
//                 <input type="hidden" id="var1" name="var1" value=""/>
//
//
//                 <div align="center" id="logout">
//                   <button>Logout</button>
//                 </div>
//                 </form>
//                <p/>
//                <SearchDefForm submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
//                <p/>
//                <DiningSearches submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
//              </Route>
//              <Route>
//                <Redirect to="/home"/>
//              </Route>
//            </Switch>       
//      </div>
//    </Router>
    <div align="left"  className="App">
      	<div className="bannerHeader"> 
		      <div className="bannerCenter">
		    	 <label className="bannerLabel">Hungryman Dining Availability Search</label>
		   	  </div>      	
		    </div>
        <div className="container welcomeContent">
              The Hungryman Dining Availability application continously searches for dining availability based on desired search
              parameters.  Once a search is submitted, the Hugryman system will continously scour different sources for dining
              availability that match your preferences and display dining availability in near real time.  Searches will continue even 
              after you leave the application, so you may return a later time to check to see if any new availability has been found.
		    </div>        
       <SearchDefForm submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
       <p/>
       <DiningSearches submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
    </div>
  );
}

export default App;
