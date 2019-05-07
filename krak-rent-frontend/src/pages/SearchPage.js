import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import PropertySelect from "../components/PropertySelect";
import { bathsAmount, cityRegions, lessor, roomAmount, trueOrFalse } from "../components/search-options";
import '../css/search-page.css';

class SearchPage extends Component {

  constructor(props) {
    super(props)
    this.state = {
      region: [{ value: '-', label: 'Wszystko' }],
      lessor: [{ value: '-', label: 'Wszystko' }],
      level: [{ value: '-', label: 'Wszystko' }],
      rooms: [{ value: '-', label: 'Wszystko' }],
      baths: [{ value: '-', label: 'Wszystko' }],
      parking: [{ value: '-', label: 'Wszystko' }],
      smokers: [{ value: '-', label: 'Wszystko' }],
      pets:  [{ value: '-', label: 'Wszystko' }]
    }
    
    this.goResultsPage = this.goResultsPage.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }

  stringifySearchBody() {
    return JSON.stringify({ 
        region: this.state.region,
        lessor: this.state.lessor,
        level: this.state.level,
        rooms: this.state.rooms,
        baths: this.state.baths,
        parking: this.state.parking,
        smokers: this.state.smokers,
        pets: this.state.pets
      }
    )
  }

  handleChange (event, componentName) {
    this.setState({[componentName]: event})
  }

  goResultsPage() {
    let searchBody = this.stringifySearchBody()
    this.props.history.push({
      pathname: '/announcements',
      state: { searchJsonBody: searchBody}
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