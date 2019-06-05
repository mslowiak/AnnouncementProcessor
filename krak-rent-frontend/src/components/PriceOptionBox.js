import React from "react";
import "../css/add-announcement-page.css";

const PriceOptionBox = props => {
  return (
    <div className="price-option-box">
      {props.priceName}: {props.value}
      <div className="remove-button" onClick={(el) => props.onClick(el)}>
        [X]
      </div>
    </div>
  );
};

export default PriceOptionBox;
