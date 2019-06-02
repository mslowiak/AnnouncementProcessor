import React, { Component } from "react";
import { Card, Row, Col } from "react-bootstrap";
import AnnouncementGallery from "../components/AnnouncementGallery";
import Axios from "axios";

class AnnouncementInfoPage extends Component {
  getOfferId() {
    return this.props.location.pathname.split("/")[2];
  }

  componentDidMount() {
    Axios.get(
      "/announcements/get/" + this.getOfferId() + "/detailed-info"
    ).then(results => {
      console.log(results.data);
      this.setState({
        announcement: results.data.content
      });
    });
  }

  render() {
    return (
      <div>
        <Card bg="light">
          <Card.Header>Tytuł</Card.Header>
          <Card.Body>
            <Card.Text>
              <AnnouncementGallery />
              <hr />
              <span>kolumna 1</span>
              <span>kolumna 2</span>
              <hr />
              Opis...
            </Card.Text>
          </Card.Body>
        </Card>
      </div>
    );
  }
}

export default AnnouncementInfoPage;
