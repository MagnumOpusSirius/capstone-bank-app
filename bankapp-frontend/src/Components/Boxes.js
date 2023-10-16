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
        <Checkbox
            label="Savings Account"
            value={checkedSavings}
            onChange={handleChangeSavings}
        />
        <Checkbox
            label="Current Account"
            value={checkedCurrent}
            onChange={handleChangeCurrent}
        />
        </div>
    );
    };
    export default Boxes