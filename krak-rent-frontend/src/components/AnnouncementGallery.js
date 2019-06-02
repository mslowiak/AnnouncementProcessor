import React, { Component } from "react";
import { Carousel, CarouselItem } from "react-bootstrap";
import "../css/carousel-gallery.css";

const AnnouncementGallery = props => {
  return (
    <Carousel>
      <CarouselItem>
        <img
          className="carousel-image"
          src="https://thefederalist.com/wp-content/uploads/2017/10/overthegardenwall-998x690.jpg"
        />
      </CarouselItem>
      <CarouselItem>
        <img
          className="carousel-image"
          src="http://www.lopuchowko.poznan.lasy.gov.pl/documents/688768/33184809/wie%C5%BCa+widokowa.jpg"
        />
      </CarouselItem>
      <CarouselItem>
        <img
          className="carousel-image"
          src="https://www.schoenbrunn.at/fileadmin/user_upload/das-shloss.jpg"
        />
      </CarouselItem>
    </Carousel>
  );
};

export default AnnouncementGallery;
