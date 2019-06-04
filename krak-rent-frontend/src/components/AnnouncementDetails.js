import React from "react";
import { Container, Row } from "react-bootstrap";
import translation from "../pl-translation.json";
import "../css/announcement-details.css";

const AnnouncementDetails = props => {
  // TODO: no translation available
  // TODO: there are elements in props such as key:value, key:dict_value, key:dict_of_dicts... building a tree based component automatically would be nice
  const getFieldDetails = (targetKey, detailsTarget) => {
    let details = [];
    let containsData = false;
    // TODO: change to more elegant solution
    for (var key in detailsTarget) {
      if (detailsTarget.hasOwnProperty(key)) {
        if (detailsTarget[key] !== null && key !== "id") {
          containsData = true;
          details.push(
            <span className="details-column">
              <b>{translation[key]}: </b>
              {typeof detailsTarget[key] !== "boolean"
                ? detailsTarget[key]
                : detailsTarget[key]
                ? "tak"
                : "nie"}
            </span>
          );
        }
      }
    }
    return containsData ? (
      <Container className="details-container">
        <Row>
          <h5>{translation[targetKey]}</h5>
        </Row>
        <Row>{details}</Row>
      </Container>
    ) : (
      []
    );
  };

  const getDetails = () => {
    let details = [];
    for (var key in props.announcement) {
      if (
        props.announcement.hasOwnProperty(key) &&
        !Array.isArray(props.announcement[key]) &&
        typeof props.announcement[key] === "object"
      ) {
        if (props.announcement[key] !== null && key !== "id") {
          details.push(getFieldDetails(key, props.announcement[key]));
        }
      }
    }
    return details;
  };

  const getPriceDetails = () => {
    let additionalCosts = [];
    for (var el in props.announcement.additionalCosts) {
      const value =
        props.announcement.additionalCosts[el]["costPrice"] === null
          ? ""
          : ": " + props.announcement.additionalCosts[el]["costPrice"];
      const comma =
        el == props.announcement.additionalCosts.length - 1 ? "" : ", ";
      additionalCosts.push(
        <div>
          {props.announcement.additionalCosts[el]["costName"]}
          {value}
          {comma}
        </div>
      );
    }
    return (
      <Container className="details-container">
        <Row>
          <h5>
            {translation["baseCost"]}: <b>{props.announcement["baseCost"]}</b>
          </h5>
        </Row>
        <Row>
          <b>{translation["additionalCosts"]}: </b>
          {additionalCosts}
        </Row>
      </Container>
    );
  };

  const getSourceDetails = () => {
    return (
      <Container className="details-container">
        <Row>
          <span>
            {translation["provider"]}:{" "}
            <a href={props.announcement["url"]}>
              {props.announcement["provider"]}
            </a>{" "}
          </span>{" "}
          <span>
            {translation["creationDate"]}: {props.announcement["creationDate"]}
          </span>
        </Row>
      </Container>
    );
  };

  const isDataLoaded = Object.keys(props.announcement).length > 0;
  const details = isDataLoaded ? getDetails() : [];
  const priceDetails = isDataLoaded ? getPriceDetails() : [];
  const sourceDetails = isDataLoaded ? getSourceDetails() : [];

  return (
    <Container>
      {priceDetails}
      {details}
      {sourceDetails}
    </Container>
  );
};

export default AnnouncementDetails;
