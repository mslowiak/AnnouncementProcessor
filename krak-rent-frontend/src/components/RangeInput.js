import React, { Component } from 'react'
import { Form } from 'react-bootstrap';
import '../css/search-page.css'

class RangeInput extends Component {
    render() {
        return (
            <div className="row">
                <div className="range-label">{this.props.labelName}:</div>
                <div className="range-separator-label">Od:</div>
                <Form.Control 
                    className="range-input" 
                    type="number" 
                    value={this.props.from}
                    onChange={e => this.props.handler(e, this.props.name + "From")}
                />
                <div className="range-separator-label">Do:</div>
                <Form.Control 
                    className="range-input" 
                    type="number" 
                    value={this.props.to} 
                    onChange={e => this.props.handler(e, this.props.name + "To")}
                />
            </div>
        );
    }
}

export default RangeInput;