import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import AddBusinessIcon from '@mui/icons-material/AddBusiness';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import UserNavbar from '../navigation/UserNavbar';
import { useState } from 'react';
import axios from 'axios';
import {toast} from "react-toastify"
import { useNavigate } from 'react-router-dom';



function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Lending Mock
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();



export default function BusinessLoan() {
  const navigate = useNavigate()
  const baseUrl = "http://localhost:8082/v1/businessLoan/"

  const [business,setBusiness] = useState({
    businessName:"",
    customerMobileNo:sessionStorage.getItem("phoneNumber"),
    loanName:sessionStorage.getItem("loanName"),
    loanamount:sessionStorage.getItem("loanAmount"),
    rateOfInterest:sessionStorage.getItem("rateOfInterest")
  })

  const handleChange = (e) =>{
    setBusiness({...business,[e.target.name]:e.target.value})
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    axios.post(baseUrl,business)
    .then((response) =>{
      console.log(response.data)
      if(response.data){
        toast.success("Applied for loan")
        navigate("/status")
      }
    })
  };

  return (
    <>
    <UserNavbar/>
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <AddBusinessIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            Business Loan Application Form
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}  sm={6}>
              <TextField
                  autoComplete="given-name"
                  name="name"
                  required
                  fullWidth
                  id="name"
                  type="text"
                  label="Full Name"
                  value={sessionStorage.getItem("name")}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  id="dateOfBirth"
                  label="Date of Birth"
                  name="dateOfBirth"
                  type="date"
                  value={sessionStorage.getItem("dateOfBirth")}
                  InputProps={{
                    readOnly: true,
                  }}
                  
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  id="phoneNumber"
                  label="Phone Number"
                  name="phoneNumber"
                  type="number"
                  value={sessionStorage.getItem("phoneNumber")}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  type="email"
                  value={sessionStorage.getItem("email")}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="adhar"
                  label="Aadhaar Number"
                  type="text"
                  id="adhar"
                  value={sessionStorage.getItem("adhar")}
                  InputProps={{
                    readOnly: true,
                  }}
                  
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="panNumber"
                  label="PAN Number"
                  type="text"
                  id="panNumber"
                  value={sessionStorage.getItem("panNumber")}
                  InputProps={{
                    readOnly: true,
                  }}
                  
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="rateOfInterest"
                  label="Intrest rate"
                  type="text"
                  id="rateOfInterest"
                  value={sessionStorage.getItem("rateOfInterest")}
                  InputProps={{
                    readOnly: true,
                  }}
                  
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="loanAmount"
                  label="Loan Amount"
                  type="text"
                  id="loanAmount"
                  value={sessionStorage.getItem("loanAmount")}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}>
                  <TextField
                      required
                      fullWidth
                      name="businessName"
                      label="Name of Your Business"
                      type="text"
                      id="businessName"
                      onChange={handleChange}
                    />
                </Grid>
                
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Apply for loan
            </Button>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}