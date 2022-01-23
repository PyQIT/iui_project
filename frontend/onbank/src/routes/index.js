import { paths } from 'routes/paths';
import Transactions from 'pages/Transactions/Transactions';
import TransactionDetails from 'pages/Transactions/TransactionDetails/TransactionDetails';
import TransactionTable from 'pages/Transactions/TransactionsTable/TransactionsTable';
import CompletedTransactions from 'pages/Transactions/TransactionsTable/ComplitedTransactions/ComplitedTransactions';
import PendingTransactions from 'pages/Transactions/TransactionsTable/PendingTransactions/PendingTransactions';
import NewTransfer from 'pages/NewTransfer/NewTransfer';
import NewDeposit from "../pages/NewDeposit/NewDeposit";
import Login from "../pages/User/Login";
import Register from "../pages/User/Register";

export const routes = [
  {
    path: paths.transactions,
    component: Transactions,
    routes: [
      {
        path: paths.detailsTransaction,
        component: TransactionDetails,
      },
      {
        path: paths.transactions,
        component: TransactionTable,
        routes: [
          {
            path: paths.completedTransactions,
            component: CompletedTransactions,
          },
          {
            path: paths.pendingTransactions,
            component: PendingTransactions,
          },
        ],
      },
    ],
  },
  {
    path: paths.newTransfer,
    component: NewTransfer,
  },
  {
    path: paths.newDeposit,
    component: NewDeposit,
  },
  {
    path: paths.login,
    component: Login,
  },
  {
    path: paths.register,
    component: Register,
  },
];
