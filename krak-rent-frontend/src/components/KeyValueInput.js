import React from "react";
import { Form, Button } from "react-bootstrap";
import "../css/add-announcement-page.css";

const KeyValueInput = props => {
  return (
    <div className="row">
      <div className="value-label">{props.label}</div>
      <Form.Control
        className="value-input"
        name={props.keyName}
        type="text"
        value={props.keyValue}
        onChange={e => props.handler(e, props.keyName)}
      />
      <Form.Control
        className="value-input"
        name={props.valueName}
        type={props.type}
        value={props.valueValue}
        onChange={e => props.handler(e, props.valueName)}
      />
      <Button
        variant="success"
        style={{ fontSize: 30 }}
        onClick={() => props.onClick}
      >
        +
      </Button>
    </div>
  );
};

export default KeyValueInput;
