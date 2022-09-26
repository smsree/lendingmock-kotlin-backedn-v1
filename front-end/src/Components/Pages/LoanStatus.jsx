import React from 'react'
import UserNavbar from './navigation/UserNavbar'
import { useEffect,useState } from 'react'
import axios from 'axios'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Typography } from '@mui/material';
function LoanStatus() {

  const [businessLoan,setBusinessLoan] = useState({
    "businessLoanId":"",
    "businessName":"",
    "customerMobileNo":null,
    "loanName":"",
    "loanamount":null,
    "rateOfInterest":null,
    "status":""
  })
  const[educationalLoan,setEducationalLoan] = useState({
    "educationalLoanId":"",
    "customerMobileNo":null,
    "collegeName":"",
    "loanName":"",
    "loanamount":null,
    "rateOfInterest":null,
    "status":""
  })
  const[vehicleLoan,setVehicleLoan] = useState({
    "vehicleLoanId":"",
    "vehicleName":"",
    "customerMobileNo":null,
    "loanName":"",
    "loanamount":null,
    "rateOfInterest":null,
    "status":""
  })
  const[housingLoan,setHousingLoan] = useState({
    "housingLoanId":"",
    "cusotmerMobileNo":null,
    "address":"",
    "loanName":"",
    "loanamount":null,
    "rateOfInterest":null,
    "status":""
  })

  
  useEffect(()=>{
    var customerMobileNo = sessionStorage.getItem("phoneNumber")
    console.log('he')
      axios.get("http://localhost:8081/v1/businessLoan/customerMobileNo/"+customerMobileNo)
      .then((response)=>{
        setBusinessLoan(response.data)
        console.log(response.data)
      })
      .catch((error)=>{
        console.log(error)
      })
      axios.get("http://localhost:8081/v1/educationalLoan/customerMobileNo/"+customerMobileNo)
      .then((res)=>{
        setEducationalLoan(res.data)
        console.log(res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
      axios.get("http://localhost:8081/v1/housingLoan/customerMobileNo/"+customerMobileNo)
      .then((res)=>{
        setHousingLoan(res.data)
        console.log(res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
      axios.get("http://localhost:8081/v1/vehicleLoan/customerMobileNo/"+customerMobileNo)
      .then((res)=>{
        setVehicleLoan(res.data)
        console.log(res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
  },[])

  return (
    <>
    <UserNavbar/>
    <Typography sx={{mx:5}} variant="h5">Status of the applied loan</Typography>
    
     <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>loanId</TableCell>
            <TableCell align="right">loan name</TableCell>
            <TableCell align="right">loan amount</TableCell>
            <TableCell align="right">ROI</TableCell>
            <TableCell align="right">customer mobile no</TableCell>
            <TableCell align="right">status</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
         
            <TableRow
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {businessLoan.businessLoanId}
              </TableCell>
              <TableCell align="right">{businessLoan.loanName}</TableCell>
              <TableCell align="right">{businessLoan.loanamount}</TableCell>
              <TableCell align="right">{businessLoan.rateOfInterest}</TableCell>
              <TableCell align="right">{businessLoan.customerMobileNo}</TableCell>
              <TableCell align="right">{businessLoan.status}</TableCell>
            </TableRow>
          
        </TableBody>
        <TableBody>
         
            <TableRow
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {educationalLoan.educationalLoanId}
              </TableCell>
              <TableCell align="right">{educationalLoan.loanName}</TableCell>
              <TableCell align="right">{educationalLoan.loanamount}</TableCell>
              <TableCell align="right">{educationalLoan.rateOfInterest}</TableCell>
              <TableCell align="right">{educationalLoan.customerMobileNo}</TableCell>
              <TableCell align="right">{educationalLoan.status}</TableCell>
            </TableRow>
          
        </TableBody>
        <TableBody>
         
            <TableRow
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {vehicleLoan.vehicleLoanId}
              </TableCell>
              <TableCell align="right">{vehicleLoan.loanName}</TableCell>
              <TableCell align="right">{vehicleLoan.loanamount}</TableCell>
              <TableCell align="right">{vehicleLoan.rateOfInterest}</TableCell>
              <TableCell align="right">{vehicleLoan.customerMobileNo}</TableCell>
              <TableCell align="right">{vehicleLoan.status}</TableCell>
            </TableRow>
          
        </TableBody>
        <TableBody>
         
            <TableRow
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {housingLoan.housingLoanId}
              </TableCell>
              <TableCell align="right">{housingLoan.loanName}</TableCell>
              <TableCell align="right">{housingLoan.loanamount}</TableCell>
              <TableCell align="right">{housingLoan.rateOfInterest}</TableCell>
              <TableCell align="right">{housingLoan.customerMobileNo}</TableCell>
              <TableCell align="right">{housingLoan.status}</TableCell>
            </TableRow>
          
        </TableBody>
      </Table>
    </TableContainer>
    
    
    </>
  )
}

export default LoanStatus