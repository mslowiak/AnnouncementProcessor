import React, { Component } from 'react';
import { Button } from 'react-bootstrap';
import PropertySelect from "../components/PropertySelect";
import RangeInput from "../components/RangeInput";
import { bathsAmount, cityRegions, lessor, roomAmount, trueOrFalse } from "../components/search-options";
import '../css/search-page.css';

class SearchPage extends Component {

  constructor(props) {
    super(props)
    this.state = {
      region: [{ value: '-', label: 'Wszystko' }],
      lessor: [{ value: '-', label: 'Wszystko' }],
      rooms: [{ value: '-', label: 'Wszystko' }],
      baths: [{ value: '-', label: 'Wszystko' }],
      parking: [{ value: '-', label: 'Wszystko' }],
      smokers: [{ value: '-', label: 'Wszystko' }],
      pets:  [{ value: '-', label: 'Wszystko' }],
      priceFrom: "",
      priceTo: "",
      areaFrom: "",
      areaTo: ""
    }
    
    this.goResultsPage = this.goResultsPage.bind(this)
    this.handleChange = this.handleChange.bind(this)
    this.updateInputValue = this.updateInputValue.bind(this)
  }

  stringifySearchBody() {
    return JSON.stringify({ 
        region: this.state.region.map(x => x.value),
        lessor: this.state.lessor.map(x => x.value),
        rooms: this.state.rooms.map(x => x.value),
        baths: this.state.baths.map(x => x.value),
        parking: this.state.parking.map(x => x.value),
        smokers: this.state.smokers.map(x => x.value),
        pets: this.state.pets.map(x => x.value),
        priceFrom: parseInt(this.state.priceFrom),
        priceTo: parseInt(this.state.priceTo),
        areaFrom: parseInt(this.state.areaFrom),
        areaTo: parseInt(this.state.areaTo)
      }
    )
  }

  handleChange (event, componentName) {
    this.setState({[componentName]: event})
  }

  updateInputValue(event, name) {
      this.setState({[name]: event.target.value})
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
      <div className="form">
        <RangeInput name="price" labelName="Cena" handler={this.updateInputValue} from={this.state.priceFrom} to={this.state.priceTo}/>
        <RangeInput name="area" labelName="Powierzchnia" handler={this.updateInputValue} from={this.state.areaFrom} to={this.state.areaTo}/>
        <PropertySelect name="region" labelName="Rejon miasta" options={cityRegions} handler={this.handleChange}/>
        <PropertySelect name="lessor" labelName="Wynajmujący" options={lessor} handler={this.handleChange}/>
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