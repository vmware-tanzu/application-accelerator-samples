import { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import DiningSearches from './DiningSearches'
import SearchDefForm from './SearchDefForm'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';

function App() {

  const [submittedSearches, setSubmittedSearches] = useState();

  const secEnabled = (process.env.REACT_APP_PROFILE === 'secure');

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
    <Router>
      <div align="left"  className="App">
            <div className="bannerHeader"> 
              <div className="bannerCenter">
              <label className="bannerLabel">Where For Dinner Dining Availability Search</label>
              </div>      	
            </div>
            <div className="container welcomeContent">
                      The Where For Dinner Dining Availability application continously searches for dining availability based on desired search
                      parameters.  Once a search is submitted, the Where For Dinner system will continously scour different sources for dining
                      availability that match your preferences and display dining availability in near real time.  Searches will continue even 
                      after you leave the application, so you may return a later time to check to see if any new availability has been found.
            </div>          
            <Switch>
              <Route path="/home">
                <div align="center" id="login">
                  <button onClick={login}>Login</button>
                </div>
              </Route>
              <Route path="/diningsearch">
                <form action="/scg-logout" method="GET" id="form">
                  <input type="hidden" id="logout" name="redirect" value="/home"/>
                  {secEnabled ?                  
                    <div align="center" id="logout">
                      <button>Logout</button>
                    </div> 
                    : null 
                  }
                </form>
                <p/>
                <SearchDefForm submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
                <p/>
                <DiningSearches submittedSearches={submittedSearches} setSubmittedSearches={setSubmittedSearches}/>
              </Route>
              <Route>
                {secEnabled ? <Redirect to="/home"/> : <Redirect to="/diningsearch"/> }
              </Route>
            </Switch>       
      </div>
    </Router>
  );
}

export default App;
