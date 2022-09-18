function TimeWindow(props)
{
    return (
        <div>
            <label className="timeWindow">- {new Date(props.timeWin.startTime).toLocaleDateString() + " " + new Date(props.timeWin.startTime).toLocaleTimeString()
            + " - " + new Date(props.timeWin.endTime).toLocaleDateString() + " " + new Date(props.timeWin.endTime).toLocaleTimeString()}
            </label>
        </div>
    );
}

export default TimeWindow;