import React, { Component } from 'react'
import Axios from 'axios'
import GeneralAnnouncementContainer from "../components/GeneralAnnouncementContainer"
import { Button } from 'react-bootstrap'
import '../css/home-page.css'

class HomePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      announcements: []
    };
    this.goSearchPage = this.goSearchPage.bind(this)
  }

  goSearchPage() {
    this.props.history.push('/search')
  }

  componentDidMount() {
    Axios.get('announcements/get/all')
      .then(
        results => {
          this.setState({
            announcements: results.data.content
          })
        }
      )
  }

  render() {
    const dataComponent = this.state.announcements && this.state.announcements.length ? <GeneralAnnouncementContainer data={this.state.announcements} /> : <div>Loading...</div>
    return (
      <div>
        <div className="search-banner">
          <Button 
            variant="secondary" 
            style={{ fontSize: 30 }}
            onClick={this.goSearchPage}
          >
            Wyszukaj interesujące Cie ogłoszenia!
          </Button>
        </div>
        {dataComponent}
      </div>
    );
  }
}

export default HomePage;