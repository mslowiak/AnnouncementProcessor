import React, { Component } from 'react'
import Select from 'react-select'
import '../css/search-page.css'

class PropertySelect extends Component {
    render() {
        return (
            <div className="row">
                <div className="left">{this.props.labelName}:</div>
                <Select
                    className="right"
                    defaultValue={this.props.options[0].value}
                    isMulti={true}
                    isSearchable={false}
                    options={this.props.options}
                    onChange={event => this.props.handler(event, this.props.name)}
                />
            </div>
        );
    }
}

export default PropertySelect;