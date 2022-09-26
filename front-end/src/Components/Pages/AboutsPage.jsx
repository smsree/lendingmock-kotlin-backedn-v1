import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LocationCityIcon from '@mui/icons-material/LocationCity';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';




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



export default function AboutsPage() {
  return (
    <>
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
            <LocationCityIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            About Lending Mock application
          </Typography>
          <Box sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Typography variant='body1' color='text.secondary' justifyContent='center' align='justify'>
                This Lending Mock Application was deleoped as a combined efforts of Aryaman Yugveer Singh and Sree Samgemesh.
                Under the direct and effective supervision of Mr. Shishram Karwal and Nishchith Kulkarni. <br/>
                The lending Mock is an application which 
                provides special loan options to a customer and based on that specialized offer the user/customer can apply for the loan and check
                the status of their applied loan.
                Tech stack used in the Development of this application:-
              </Typography>
              <Grid item xs={12}  sm={6}>
                <Typography variant='body1' color='text.secondary' justifyContent='center' align= 'justify'>
                    Backend:-
                    <ul>
                        <li>Kotlin</li>
                        <li>WebFlux</li>
                        <li>Mongo Db</li>
                    </ul>
                </Typography>
              </Grid>
              <Grid item xs={12}  sm={6}>
                <Typography variant='body1' color='text.secondary' justifyContent='center' align= 'justify'>
                    Frontend:-
                    <ul>
                        <li>React Js</li>
                        <li>Material Ui</li>
                    </ul>
                </Typography>
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Back to Login/Register
            </Button>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}