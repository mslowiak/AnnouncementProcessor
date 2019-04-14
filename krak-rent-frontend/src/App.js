import React, { Component } from 'react'
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom'

import Navbar from './components/Navbar'
import AddAnnouncementPage from "./pages/AddAnnouncementPage"
import AnnouncementInfoPage from "./pages/AnnouncementInfoPage"
import HomePage from "./pages/HomePage"
import SearchPage from "./pages/SearchPage"
import SearchResultsPage from "./pages/SearchResultsPage"

class App extends Component {
  render() {
    return (
      <div>
        <Navbar/>
        <Router>
          <div className="container">
            <Route exact path="/" component={HomePage} />
            <Route path="/add" component={AddAnnouncementPage} />
            <Route path="/offer/:id" component={AnnouncementInfoPage} />
            <Route path="/search" component={SearchPage} />
            <Route path="/announcements" component={SearchResultsPage} />
          </div>
        </Router>
      </div>
    )
  }
}

export default App;
