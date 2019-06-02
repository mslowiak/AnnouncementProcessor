import React, { Component } from "react";
import { Card } from "react-bootstrap";
import AnnouncementGallery from "../components/AnnouncementGallery";
import AnnouncementDetails from "../components/AnnouncementDetails";
import Axios from "axios";

class AnnouncementInfoPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      announcement: {}
    };
  }

  getOfferId() {
    return this.props.location.pathname.split("/")[2];
  }

  componentDidMount() {
    Axios.get(
      "/announcements/get/" + this.getOfferId() + "/detailed-info"
    ).then(results => {
      // console.log(results.data);
      this.setState({
        announcement: results.data
      });
    });
  }

  getListOfImages() {
    return this.state.announcement.images.split("\\");
  }

  render() {
    const isDataLoading = Object.keys(this.state.announcement).length === 0;
    const title = isDataLoading ? "Loading..." : this.state.announcement.title;
    const listOfImages = isDataLoading ? [] : this.getListOfImages();
    const description = isDataLoading
      ? "Loading..."
      : this.state.announcement.description;
    return (
      <div>
        <Card bg="light">
          <Card.Header>{title}</Card.Header>
          <Card.Body>
            <AnnouncementGallery images={listOfImages} />
            <hr />
            <AnnouncementDetails announcement={this.state.announcement} />
            <hr />
            {description}
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default AnnouncementInfoPage;
