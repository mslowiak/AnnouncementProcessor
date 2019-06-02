import React from "react";
import { Carousel, CarouselItem } from "react-bootstrap";
import "../css/carousel-gallery.css";

const AnnouncementGallery = props => {
  const carouselItems =
    props.images.length > 0
      ? props.images.map(imgUrl => {
          return (
            <CarouselItem>
              <img className="carousel-image" src={imgUrl} />
            </CarouselItem>
          );
        })
      : [];

  return <Carousel>{carouselItems}</Carousel>;
};

export default AnnouncementGallery;
