import React, { Component } from 'react';
import moment from 'moment';
import '../css/general-announcement.css'

class GeneralAnnouncement extends Component {
    cutString(text, cutLength) {
        if (text.length > cutLength) {
            return text.substring(0, cutLength) + '...';
        } else {
            return text;
        }
    }

    convertDateTime(dateTime) {
        var currentDate = moment();
        var offSet = currentDate.utcOffset();
        var comparedDateTime = moment(dateTime).utcOffset(offSet);

        var isSameDay = moment(currentDate).isSame(comparedDateTime, 'day');
        if (isSameDay) {
            var hours = currentDate.diff(comparedDateTime, 'hours', true);
            if (hours < 1) {
                var minutes = currentDate.diff(comparedDateTime, 'minutes', true);
                return Math.round(minutes) + " min temu";
            }
            return Math.round(hours) + " godz temu";
        } else {
            return comparedDateTime.format('DD-MM-YYYY');
        }
    }

    titleLength = 50
    descriptionLength = 100

    render() {
        const item = this.props.item;
        const localization = item.localization ? item.localization : "";
        const dateTime = this.convertDateTime(item.creationDate)
        const title = this.cutString(item.title, this.titleLength);
        const description = this.cutString(item.description, this.descriptionLength);
        return (
            <div className="frame">
                <div className="image-container">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Temp_plate_blue.svg/601px-Temp_plate_blue.svg.png" alt="" />
                </div>
                <div className="info-container">
                    <div className="details">
                        <div className="title">{title}</div>
                        <div className="description">{description}</div>
                        <div className="bottom">
                            <div className="localization">{localization}</div>
                            <div className="creation-date">dodano: {dateTime}</div>
                        </div>
                    </div>
                    <div className="pricing">
                        <div className="price">{item.baseCost} zł</div>
                        <div>{item.provider}</div>
                        <div>{item.lessorType}</div>
                    </div>
                </div>
            </div>
        );
    }
}

export default GeneralAnnouncement;