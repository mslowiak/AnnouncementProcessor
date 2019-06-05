import React from "react";
import "../css/add-announcement-page.css"

const FormContainer = props => {

    return (
    <div className="form-container">
        {props.title}
        {props.forms}  
    </div>)

};

export default FormContainer;