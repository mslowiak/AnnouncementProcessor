import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import translation from "../pl-translation.json";

const AnnouncementDetails = props => {
  const getLessorDetails = () => {
    let lessorDetails = [];
    // TODO: change to more elegant solution
    console.log(props.announcement.lessor);
    const lessor = props.announcement.lessor;
    for (var key in lessor) {
      if (lessor.hasOwnProperty(key)) {
        if (lessor[key] != null && key != "id") {
          lessorDetails.push(
            <Col>
              {translation[key]}: {lessor[key]}
            </Col>
          );
        }
      }
    }
    // for (const [key, value] of props.announcement.lessor.entries()) {
    //   if (value != null) {
    //     lessorDetails.push(
    //       <Col>
    //         {translation[key]}: {value}
    //       </Col>
    //     );
    //   }
    // }
    return (
      <Container>
        <Row>{translation["lessor"]}</Row>
        <Row>{lessorDetails}</Row>
      </Container>
    );
  };

  const isDataLoaded = Object.keys(props.announcement).length > 0;
  const lessorDetails = isDataLoaded ? getLessorDetails() : [];

  return (
    <Container>
      {lessorDetails}
      <Row>
        <Col>1 of 3</Col>
        <Col>2 of 3</Col>
        <Col>3 of 3</Col>
      </Row>
    </Container>
  );
};

export default AnnouncementDetails;
