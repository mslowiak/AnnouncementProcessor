import React, { Component } from "react";
import FormContainer from "../components/FormContainer";
import ValueInput from "../components/ValueInput";
import KeyValueInput from "../components/KeyValueInput";

class AddAnnouncementPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "",
      basePrice: null,
      additionalCosts: [],
      tmpOptionalPriceKey: null,
      tmpOptionalPrice: null
    };
    this.handleChange = this.handleChange.bind(this);
    this.updateInputValue = this.updateInputValue.bind(this);
  }

  handleChange(event, componentName) {
    this.setState({ [componentName]: event });
  }

  updateInputValue(event, name) {
    this.setState({ [name]: event.target.value });
  }

  addTmpToPrices() {
    let additionalCosts = this.props.additionalCosts.slice();
    this.setState({ ["additionalCosts"]: additionalCosts });
  }

  render() {
    console.log("crr title: " + this.state.title);
    console.log(this.state.basePrice);
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
        keyValue={this.props.tmpOptionalPriceKey}
        valueValue={this.props.tmpOptionalPrice}
        handler={this.updateInputValue}
        onClick={this.addTmpToPrices}
      />
    ];
    return (
      <div>
        <h2>Dodaj ogłoszenie</h2>
        <FormContainer title="Tytuł" forms={titleForm} />
        <FormContainer title="Cena" forms={priceForms} />
      </div>
    );
  }
}

export default AddAnnouncementPage;

{
  /* <KeyValueInput
        type="number"
        keyName="tmpOptionalPriceKey"
        valueName="tmpOptionalPrice"
        label="Dodatkowe opłaty"
        keyValue={this.props.tmpOptionalPriceKey}
        valueValue={this.props.tmpOptionalPrice}
        handler={this.updateInputValue}
        onClick={this.addTmpToPrices}
      /> */
}

// String title;
// String images;
// PriceDto price:
//                {BigDecimal basePrice;
//                 String currency;
//                 Map<String, BigDecimal> additionalPrices;}
// LocationDto location: {String country;
//                        String city;
//                        String street;
//                        String zipCode;
//                        String buildingNumber;
//                        String flatNumber;
//                        String district;}
// PropertyDataDto propertyData: {String propertyType;
//                                Double area;
//                                Boolean isSmokingAllowed;
//                                Boolean isPerFriendly;
//                                Integer roomNumber;
//                                Integer bathroomNumber;
//                                String parkingAvailability;
//                                Integer level;
//                                String furnishing;}
// LessorDto lessor: {String name;
//                    String lessorType; // to enum
//                    String phoneNumber;
//                    String email;}
// LocalDateTime creationDate;
// String url;
// String description;
// String provider;
