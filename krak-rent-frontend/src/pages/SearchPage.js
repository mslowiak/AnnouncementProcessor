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
      region: null,
      lessor: null,
      rooms: null,
      baths: null,
      parking: null,
      smokers: null,
      pets: null,
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
    console.log(this.state.lessor)
    return JSON.stringify({ 
        region: this.state.region == null ? null : this.state.region.map(x => x.value),
        lessor: this.state.lessor == null ? null : this.state.lessor.map(x => x.value),
        rooms: this.state.rooms == null ? null : this.state.rooms.map(x => x.value),
        baths: this.state.baths == null ? null : this.state.baths.map(x => x.value),
        parking: this.state.parking == null ? null : this.state.parking.map(x => x.value),
        smokers: this.state.smokers == null ? null : this.state.smokers.map(x => x.value),
        pets: this.state.pets == null ? null : this.state.pets.map(x => x.value),
        priceFrom: this.state.priceFrom === "" ? null : parseInt(this.state.priceFrom),
        priceTo: this.state.priceTo === "" ? null : parseInt(this.state.priceTo),
        areaFrom: this.state.areaFrom === "" ? null : parseInt(this.state.areaFrom),
        areaTo: this.state.areaTo === "" ? null : parseInt(this.state.areaTo)
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