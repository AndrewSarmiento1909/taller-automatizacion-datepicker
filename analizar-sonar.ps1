$tokenSeguro = Read-Host 'Pega el token de SonarQube Cloud' -AsSecureString
$tokenPtr = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($tokenSeguro)
try {
    $env:SONAR_TOKEN = [System.Runtime.InteropServices.Marshal]::PtrToStringBSTR($tokenPtr)
    Push-Location -LiteralPath $PSScriptRoot
    try {
        & .\gradlew.bat sonar --console plain
        $resultado = $LASTEXITCODE
    } finally {
        Pop-Location
    }
} finally {
    Remove-Item Env:SONAR_TOKEN -ErrorAction SilentlyContinue
    [System.Runtime.InteropServices.Marshal]::ZeroFreeBSTR($tokenPtr)
    $tokenSeguro.Dispose()
}
exit $resultado
