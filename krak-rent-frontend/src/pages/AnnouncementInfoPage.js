import React, { Component } from "react";
import { Card } from "react-bootstrap";
import AnnouncementGallery from "../components/AnnouncementGallery";
import AnnouncementDetails from "../components/AnnouncementDetails";
import Axios from "axios";
import translation from "../pl-translation.json";

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

  getDetailsState() {}

  render() {
    const isDataLoading = Object.keys(this.state.announcement).length === 0;
    const title = isDataLoading ? "Loading..." : this.state.announcement.title;
    const listOfImages = isDataLoading ? [] : this.getListOfImages();
    const description = isDataLoading
      ? "Loading..."
      : this.state.announcement.description;
    const detailsProps = isDataLoading
      ? {}
      : Object.keys(this.state.announcement)
          .filter(key => key in translation)
          .reduce((obj, key) => {
            obj[key] = this.state.announcement[key];
            return obj;
          }, {});

    return (
      <div>
        <Card bg="light">
          <Card.Header>
            <h3>{title}</h3>
          </Card.Header>
          <Card.Body>
            <AnnouncementGallery images={listOfImages} />
            <hr />
            <AnnouncementDetails announcement={detailsProps} />
            <hr />
            {description}
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default AnnouncementInfoPage;
