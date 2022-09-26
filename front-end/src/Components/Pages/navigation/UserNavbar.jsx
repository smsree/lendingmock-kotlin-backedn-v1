import { AppBar, Button, IconButton, Stack, Toolbar, Typography } from '@mui/material'
import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import {toast} from 'react-toastify'

function UserNavbar() {
    const navigate = useNavigate()

    const handleLogout=e=>{
        localStorage.clear();
        sessionStorage.clear();
        toast.success("logged out!!!")
        navigate("/")
    }

    return (
        <AppBar position='static'>
            <Toolbar>
                <IconButton size='large' edge='start' color='inherit'>
                    <AutoAwesomeIcon/>
                </IconButton>
                <Typography variant='h6' component='div' sx={{flexGow: 1}}>
                    Lending Mock Application
                </Typography>
                <Stack direction='row' spacing={2} sx={{ml:'auto'}}>
                    <Button color='inherit'
                        component={Link}
                        to='/offer' 
                     >
                        Offer
                    </Button>
                    <Button color='inherit'
                        component={Link}
                        to='/status' 
                     >
                        Status
                    </Button>
                    <Button color='inherit'
                    onClick={handleLogout} 
                     >
                        Logout
                    </Button>
                </Stack>
            </Toolbar>
        </AppBar>
      )
}

export default UserNavbar