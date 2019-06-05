import React, { Component } from "react";
import FormContainer from "../components/FormContainer";
import ValueInput from "../components/ValueInput";
import KeyValueInput from "../components/KeyValueInput";
import PriceOptionBox from "../components/PriceOptionBox";
import PropertySelect from "../components/PropertySelect";
import { Button } from "react-bootstrap";
import {
  bathsAmount,
  cityRegions,
  lessor,
  roomAmount,
  trueOrFalse
} from "../components/search-options";

class AddAnnouncementPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "",

      basePrice: null,
      additionalPrices: [],
      tmpOptionalPriceKey: null,
      tmpOptionalPrice: null,

      city: "",
      street: "",
      zipCode: "",
      buildingNumber: "",
      flatNumber: "",
      district: "",

      propertyType: "",
      area: null,
      isSmokingAllowed: null,
      // TODO: fix typo here and in service
      isPerFriendly: null,
      roomNumber: null,
      bathroomNumber: null,
      parkingAvailability: null,
      level: null,
      furnishing: null,

      lessorName: "",
      lessorType: "",
      phoneNumber: "",
      email: "",

      description: ""
    };

    this.handleChange = this.handleChange.bind(this);
    this.updateInputValue = this.updateInputValue.bind(this);
    this.addTmpToPrices = this.addTmpToPrices.bind(this);
  }

  handleChange(event, componentName) {
    this.setState({ [componentName]: event });
  }

  updateInputValue(event, name) {
    this.setState({ [name]: event.target.value });
  }

  addTmpToPrices() {
    // TODO: prevent adding with the same key
    let additionalPrices = this.state.additionalPrices.slice();
    additionalPrices.push({
      [this.state.tmpOptionalPriceKey]: this.state.tmpOptionalPrice
    });
    this.setState({ ["additionalPrices"]: additionalPrices });
  }

  removeFromAdditionalPrices(keyName) {
    let additionalPrices = this.state.additionalPrices.slice();
    let filtered = additionalPrices.filter(el => {
      return Object.keys(el)[0] !== keyName;
    });
    this.setState({ ["additionalPrices"]: filtered });
  }

  getAdditionalPriceBoxes() {
    let additionalPrices = this.state.additionalPrices.slice();
    return additionalPrices.map(el => {
      return (
        <PriceOptionBox
          priceName={Object.keys(el)[0]}
          value={Object.values(el)[0]}
          onClick={() => this.removeFromAdditionalPrices(Object.keys(el)[0])}
        />
      );
    });
  }

  render() {
    console.log(this.state);
    const priceOptionBoxes = this.getAdditionalPriceBoxes();
    const titleForm = [
      <ValueInput
        type="text"
        name="title"
        label="Wprowadź tytuł ogłoszenia:"
        value={this.state.title}
        handler={this.updateInputValue}
      />
    ];
    const priceForms = [
      <ValueInput
        type="number"
        name="basePrice"
        label="Cena:"
        value={this.state.basePrice}
        handler={this.updateInputValue}
      />,
      <KeyValueInput
        type="number"
        keyName="tmpOptionalPriceKey"
        valueName="tmpOptionalPrice"
        label="Dodatkowe opłaty"
        labelKey="Opłata"
        labelValue="Koszt"
        keyValue={this.state.tmpOptionalPriceKey}
        valueValue={this.state.tmpOptionalPrice}
        handler={this.updateInputValue}
        onClick={this.addTmpToPrices}
      />,
      <div className="price-option-container">{priceOptionBoxes}</div>
    ];

    const localizationForms = [
      <ValueInput
        type="text"
        name="country"
        label="Kraj:"
        value={this.state.country}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="city"
        label="Miasto:"
        value={this.state.city}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="street"
        label="Ulica:"
        value={this.state.street}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="zipCode"
        label="Kod pocztowy:"
        value={this.state.zipCode}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="buildingNumber"
        label="Numer budynku:"
        value={this.state.buildingNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="flatNumber"
        label="Numer mieszkania:"
        value={this.state.flatNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="district"
        label="Dzielnica:"
        value={this.state.district}
        handler={this.updateInputValue}
      />
    ];

    const propertyDataForm = [
      <ValueInput
        type="text"
        name="propertyType"
        label="Rodzaj obiektu wynajmu:"
        value={this.state.propertyType}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="number"
        name="area"
        label="Powierzchnia:"
        value={this.state.area}
        handler={this.updateInputValue}
      />,
      <PropertySelect
        name="isSmokingAllowed"
        labelName="Dla palących"
        options={trueOrFalse}
        handler={this.handleChange}
      />,
      <PropertySelect
        name="isPerFriendly"
        labelName="Przyjazne zwierzakom"
        options={trueOrFalse}
        handler={this.handleChange}
      />,
      <ValueInput
        type="number"
        name="roomNumber"
        label="Ilość pokoi: "
        value={this.state.roomNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="number"
        name="bathroomNumber"
        label="Ilość łazienek: "
        value={this.state.bathroomNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="parkingAvailability"
        label="Szczegóły parkingu: "
        value={this.state.bathroomNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="number"
        name="level"
        label="Piętro: "
        value={this.state.level}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="text"
        name="furnishing"
        label="Umeblowanie: "
        value={this.state.furnishing}
        handler={this.updateInputValue}
      />
    ];

    const lessorForm = [
      <ValueInput
        type="text"
        name="lessorName"
        label="Imię: "
        value={this.state.lessorName}
        handler={this.updateInputValue}
      />,
      <PropertySelect
        name="lessorType"
        labelName="Wystawia"
        options={lessor}
        handler={this.handleChange}
      />,
      <ValueInput
        type="text"
        name="phoneNumber"
        label="Numer telefonu: "
        value={this.state.phoneNumber}
        handler={this.updateInputValue}
      />,
      <ValueInput
        type="email"
        name="email"
        label="E-mail: "
        value={this.state.email}
        handler={this.updateInputValue}
      />
    ];

    const descriptionForm = [
      <ValueInput
        type="text"
        name="description"
        label="Dodaj opis ogłoszenia: "
        value={this.state.description}
        handler={this.updateInputValue}
      />
    ];
    return (
      <div>
        <h2>Dodaj ogłoszenie</h2>
        <FormContainer title="Tytuł" forms={titleForm} />
        <FormContainer title="Koszty" forms={priceForms} />
        <FormContainer title="Lokalizacja" forms={localizationForms} />
        <FormContainer title="Szczegółowe dane" forms={propertyDataForm} />
        <FormContainer
          title="Dane wstawiającego ogłoszenie"
          forms={lessorForm}
        />
        <FormContainer title="Opis" forms={descriptionForm} />
        <Button
          variant="success"
          style={{ fontSize: 30 }}
          onClick={() => console.log("Add announcement1")}
        >
          Dodaj ogłoszenie
        </Button>
      </div>
    );
  }
}

export default AddAnnouncementPage;
