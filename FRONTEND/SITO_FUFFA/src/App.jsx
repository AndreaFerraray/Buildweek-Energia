import Home from "./component/Home";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import SignInSide from "./component/Login";
import Auth from "./component/Auth";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./component/Navbar";
import Register from "./component/Register";
import UserPage from "./component/UserPage";
import Footer from "./component/Footer";
import { Col, Row } from "react-bootstrap";
import SideBar from "./component/SideBar";
import { Logout } from "@mui/icons-material";
import Esci from "./component/Esci";
import Address from "./component/Address";

const App = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={<Home />}
          />
          <Route
            path="/login"
            element={<SignInSide />}
          />
          <Route
            path="/auth"
            element={<Auth />}
          />
          <Route
            path="/register"
            element={<Register />}
          />
          <Route
            path="/profile"
            element={<UserPage />}
          />
          <Route
            path="/logout"
            element={<Esci />}
          />
          <Route
            path="/address"
            element={<Address />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
