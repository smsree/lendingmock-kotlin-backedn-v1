import { Route, Routes } from 'react-router-dom';
import './App.css';
import AboutsPage from './Components/Pages/AboutsPage';
import AdminLogin from './Components/Pages/AdminPages/AdminLogin';
import BusinessLoan from './Components/Pages/Loans/BusinessLoan';
import EducationLoan from './Components/Pages/Loans/EducationLoan';
import Housingloan from './Components/Pages/Loans/HousingLoan';
import VehicleLoan from './Components/Pages/Loans/VehicleLoan';
import Login from './Components/Pages/Login.jsx';
import OfferPage from './Components/Pages/OfferPage';
import Register from './Components/Pages/Register';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import LoanStatus from './Components/Pages/LoanStatus';
import AdminStatus from './Components/Pages/AdminPages/AdminStatus';

function App() {
  return (
    <div className="App">
      <ToastContainer
      position="top-center"
      autoClose={5000}
      />
      <Routes>
        <Route exact path ="/" element={<Login/>}/>
        <Route exact path ="/register" element={<Register/>}/>
        <Route exact path="/housing" element={<Housingloan/>}/>
        <Route exact path="/education" element={<EducationLoan/>}/>
        <Route exact path="/business" element={<BusinessLoan/>}/>
        <Route exact path="/vehicle" element={<VehicleLoan/>} />
        <Route exact path="/about" element={<AboutsPage/>} />
        <Route exact path="/offer" element={<OfferPage/>}/>
        <Route exact path="/adminLogin-111" element={<AdminLogin/>}/>
        <Route exact path="/status" element={<LoanStatus/>}/>
        <Route exact path="/adminStatus" element={<AdminStatus/>}/>
      </Routes>
    </div>
  );
}

export default App;
