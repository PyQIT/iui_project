import {axiosInstanceGet, axiosInstancePost} from 'core/axiosConfig';

const DEPOSIT_URL = '/deposits';

const createDepositApi = (createDepositDto) =>
    axiosInstancePost({
        url: DEPOSIT_URL,
        data: {
            depositAmount: createDepositDto.depositAmount,
            expectedReturn: createDepositDto.expectedReturn
        },
    });

const getDepositApi = () =>
    axiosInstanceGet({
        url: DEPOSIT_URL
    })

const getDepositBalanceApi = () =>
    axiosInstanceGet({
        url: `${DEPOSIT_URL}/balance`,
    });

const getDepositBareApi = () => {
    return axiosInstanceGet({
        url: DEPOSIT_URL
    });
}


export {
    createDepositApi,
    getDepositApi,
    getDepositBalanceApi,
    getDepositBareApi
};