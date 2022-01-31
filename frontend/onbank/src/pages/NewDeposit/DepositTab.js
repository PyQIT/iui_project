import React from 'react';
import {getDepositBareApi} from "../../API/depositAPI";
import NewDeposit from "./NewDeposit";

class DepositTab extends React.Component {

    depositData = {};

    hasDeposit = false;

    componentDidMount = () => {
        this.checkDeposit();
    }

    checkDeposit = () => {
        getDepositBareApi()
            .then(response => {
                if (response.status === 204) {
                    this.hasDeposit = false;
                } else {
                    this.depositData = response.data;
                    this.hasDeposit = true;
                    console.log(response)
                }
            })
            .catch(error => {
                this.hasDeposit = false;
                console.log(error);
            });
    }

    render = () => {
        return (
            <div>
                {
                    this.hasDeposit
                    ? this.depositData
                    : <NewDeposit/>
                }
            </div>
        )
    }
}

export default DepositTab
