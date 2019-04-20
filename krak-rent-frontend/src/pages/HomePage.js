import React, { Component } from 'react';
import Axios from 'axios';
import GeneralAnnouncementContainer from "../components/GeneralAnnouncementContainer";

class HomePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      announcements: []
    };
  }

  componentDidMount() {
    Axios.get('announcements/get/all')
      .then(
        results => {
          this.setState({
            announcements: results.data
          })
        }
      )
  }

  render() {
    const dataComponent = this.state.announcements && this.state.announcements.length ? <GeneralAnnouncementContainer data={this.state.announcements} /> : <div>Loading...</div>
    return (
      <div>
        <div>HomePage page</div>
        {dataComponent}
      </div>
    );
  }
}

export default HomePage;