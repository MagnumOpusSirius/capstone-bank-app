import React from "react";


const Boxes = () => {
    const [checkedSavings, setCheckedSavings] = React.useState(false);
    const [checkedCurrent, setCheckedCurrent] = React.useState(false);

    const handleChangeSavings = () => {
        setCheckedSavings(!checkedSavings);
    };

    const handleChangeCurrent = () => {
        setCheckedCurrent(!checkedCurrent);
    };

    return (
        <div>
        <Boxes
            label="Savings Account"
            value={checkedSavings}
            onChange={handleChangeSavings}
        />
        <Boxes
            label="Current Account"
            value={checkedCurrent}
            onChange={handleChangeCurrent}
        />
        </div>
    );
    };

    export default Boxes