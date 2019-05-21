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
import RegisterPage from "./pages/RegisterPage"
import LoginPage from "./pages/LoginPage"
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/main.css'
import Axios from 'axios';

class App extends Component {
  prepareAxiosDefaults() {
      // Axios.defaults.baseURL = "http://krak-rent-api.herokuapp.com";
      Axios.defaults.baseURL = "http://localhost:8080";
      Axios.defaults.headers.common['Content-Type'] ='application/json;charset=utf-8';
  }

  render() {
    this.prepareAxiosDefaults()

    return (
      <Router>
        <MyNavbar/>
        <div className="container main">
          <Route exact path="/" component={HomePage} />
          <Route path="/add" component={AddAnnouncementPage} />
          <Route path="/offer/:id" component={AnnouncementInfoPage} />
          <Route path="/search" component={SearchPage} />
          <Route path="/announcements" component={SearchResultsPage} />
          <Route path="/register" component={RegisterPage} />
          <Route path="/login" component={LoginPage} />
        </div>
      </Router>
    )
  }
}


export default App;
