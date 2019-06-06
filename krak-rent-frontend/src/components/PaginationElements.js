import React from "react";
import { Pagination } from "react-bootstrap";
import "../css/search-results.css";

const PaginationElements = props => {
  const frameSize = 7;
  let leftIndex = props.activePage - Math.floor(frameSize / 2);
  leftIndex = leftIndex < 0 ? 0 : leftIndex;
  // let rightIndex = this.props.activePage + frameSize - 1 - (this.props.activePage - leftIndex);
  let rightIndex = props.activePage + Math.floor(frameSize / 2);
  rightIndex =
    rightIndex > props.totalPages - 1 ? props.totalPages - 1 : rightIndex;

  const indexes = new Array(rightIndex - leftIndex + 1)
    .fill(undefined)
    .map((_, i) => i + leftIndex);

  let paginationElems = [];

  if (leftIndex === 0) {
    paginationElems.push(<Pagination.First key="first" disabled />);
  } else {
    paginationElems.push(
      <Pagination.First key="first" onClick={() => props.onClick(0)} />
    );
  }

  paginationElems.push(
    indexes.map(i => (
      <Pagination.Item
        key={i}
        active={i === props.activePage}
        onClick={() => props.onClick(i)}
      >
        {i + 1}
      </Pagination.Item>
    ))
  );

  if (rightIndex === props.totalPages - 1) {
    paginationElems.push(<Pagination.Last key="last" disabled />);
  } else {
    paginationElems.push(
      <Pagination.Last
        key="last"
        onClick={() => props.onClick(props.totalPages - 1)}
      />
    );
  }

  return paginationElems;
};

export default PaginationElements;