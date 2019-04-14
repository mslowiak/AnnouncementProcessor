import React, { Component } from 'react'
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom'

import MyNavbar from "./components/MyNavbar";
import AddAnnouncementPage from "./pages/AddAnnouncementPage"
import AnnouncementInfoPage from "./pages/AnnouncementInfoPage"
import HomePage from "./pages/HomePage"
import SearchPage from "./pages/SearchPage"
import SearchResultsPage from "./pages/SearchResultsPage"
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
  render() {
    return (
      <Router>
        <MyNavbar/>
        <div className="container" style={{backgroundColor: "lightgray", height: "100vh"}}>
          <Route exact path="/" component={HomePage} />
          <Route path="/add" component={AddAnnouncementPage} />
          <Route path="/offer/:id" component={AnnouncementInfoPage} />
          <Route path="/search" component={SearchPage} />
          <Route path="/announcements" component={SearchResultsPage} />
        </div>
      </Router>
    )
  }
}

export default App;
