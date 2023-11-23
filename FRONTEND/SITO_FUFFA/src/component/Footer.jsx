import { Col, Container, Row } from "react-bootstrap";

const Footer = () => {
  return (
    <>
      <Container>
        <Row>
          <Col className="d-flex justify-content-center">
            <Row
              className="d-flex gap-5"
              style={{ flexGrow: "inherit" }}>
              <Col sm={4}>
                <Row className="d-flex flex-column">
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                </Row>
              </Col>
              <Col>
                <Row className="d-flex flex-column">
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                </Row>
              </Col>
              <Col>
                <Row className="d-flex flex-column">
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                </Row>
              </Col>
              <Col>
                <Row className="d-flex flex-column">
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                  <Col>Elemento 1.1</Col>
                </Row>
              </Col>
            </Row>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Footer;
