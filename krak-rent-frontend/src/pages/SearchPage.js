import React, { Component } from 'react';
import PropertySelect from "../components/PropertySelect"
import { Button } from 'react-bootstrap'
import { cityRegions, lessor, roomAmount, bathsAmount, trueOrFalse } from "../components/search-options"
import '../css/search-page.css'


class SearchPage extends Component {

  constructor(props) {
    super(props)
    this.goResultsPage = this.goResultsPage.bind(this)
    this.state = {
      region: [{ value: '-', label: 'Wszystko' }],
      lessor: '-',
      level: '-',
      rooms: '-',
      baths: '-',
      parking: '-',
      smokers: '-',
      pets: '-'
    }
    
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange (event, componentName) {
    this.setState({[componentName]: event});
    console.log(this.state);
  }

  goResultsPage() {
    this.props.history.push({
      pathname: '/announcements',
      state: { searchJsonPostBody: "dupa blada"}
    })
  }

  render() {
    return (
      <div>
        <PropertySelect name="region" labelName="Rejon miasta" options={cityRegions} handler={this.handleChange}/>
        <PropertySelect name="lessor" labelName="Wynajmujący" options={lessor} handler={this.handleChange}/>
        <PropertySelect name="level" labelName="Pietro" options={cityRegions} handler={this.handleChange}/>
        <PropertySelect name="rooms" labelName="Liczba pokoi" options={roomAmount} handler={this.handleChange}/>
        <PropertySelect name="baths" labelName="Liczba łazienek" options={bathsAmount} handler={this.handleChange}/>
        <PropertySelect name="parking" labelName="Parking" options={trueOrFalse} handler={this.handleChange}/>
        <PropertySelect name="smokers" labelName="Dla palcących" options={trueOrFalse} handler={this.handleChange}/>
        <PropertySelect name="pets" labelName="Przyjazne zwierzakom" options={trueOrFalse} handler={this.handleChange}/>
        <Button 
            variant="success" 
            style={{ fontSize: 30 }}
            onClick={this.goResultsPage}
          >
            Szukaj
          </Button>
      </div>
    );
  }
}

export default SearchPage;