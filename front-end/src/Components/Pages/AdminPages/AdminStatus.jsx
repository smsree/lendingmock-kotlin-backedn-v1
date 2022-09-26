import React from "react"
import AdminNavBar from "./AdminNavBar"
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useState,useEffect } from "react";
import Button from '@mui/material/Button';
import AdminModel from "./AdminModel";
import axios from "axios";
function AdminStatus(){
    const[user,setUser] = useState([])
    useEffect(()=>{
        axios.get("http://localhost:8091/v1/customer")
        .then((res)=>{
            setUser(res.data)
            console.log(res.data)
        })
        .catch((err)=>{
            console.log(err)
        })
    },[])

    const handleClick = event =>{
        event.preventDefault();
        <AdminModel/>
    }

    return (
        
        <>
        <AdminNavBar/>
        <div>Admin status</div>
        <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Customer name</TableCell>
            <TableCell align="center">Phone number</TableCell>
            <TableCell align="center">Adhar number</TableCell>
            <TableCell align="center">pan number</TableCell>
            <TableCell align="center">email</TableCell>
            <TableCell align="center">DOB</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {user.map((temp) => (
            <TableRow
              key={temp.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {temp.name}
              </TableCell>
              <TableCell align="center">{temp.phoneNumber}</TableCell>
              <TableCell align="center">{temp.adhar}</TableCell>
              <TableCell align="center">{temp.panNumber}</TableCell>
              <TableCell align="center">{temp.email}</TableCell>
              <TableCell align="center">{temp.dateOfBirth}</TableCell>
              <TableCell align="center"> <Button onClick={handleClick}>show details</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
        </>
    )
}
export default AdminStatus