import React from "react";
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
            <div align="left">
              <b>{translation[key]}: </b>
              {typeof detailsTarget[key] !== "boolean"
                ? detailsTarget[key]
                : detailsTarget[key]
                ? "tak"
                : "nie"}
            </div>
          );
        }
      }
    }
    return containsData ? (
      <div className="details-container">
        <div>
          <h5>{translation[targetKey]}</h5>
        </div>
        <div>{details}</div>
      </div>
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
    return (<div align="left">{details}</div>);
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
        <span>
          {props.announcement.additionalCosts[el]["costName"]}
          {value}
          {comma}
        </span>
      );
    }
    return (
      <div className="details-container">
        <div>
          <h5>
            {translation["baseCost"]}: <b>{props.announcement["baseCost"]}</b>
          </h5>
        </div>
        <div>
          <b>{translation["additionalCosts"]}: </b>
          {additionalCosts}
        </div>
      </div>
    );
  };

  const getSourceDetails = () => {
    return (
      <div className="details-container">
        <div>
          {translation["provider"]}:{" "}
          <a href={props.announcement["url"]}>
            {props.announcement["provider"]}
          </a>{" "}
        </div>{" "}
        <div>
          {translation["creationDate"]}:{props.announcement["creationDate"]}
        </div>
      </div>
    );
  };

  const isDataLoaded = Object.keys(props.announcement).length > 0;
  const details = isDataLoaded ? getDetails() : [];
  const priceDetails = isDataLoaded ? getPriceDetails() : [];
  const sourceDetails = isDataLoaded ? getSourceDetails() : [];

  return (
    <div>
      {priceDetails}
      {details}
      {sourceDetails}
    </div>
  );
};

export default AnnouncementDetails;
