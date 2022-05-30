import { MenuProps, Menu } from '@mui/material';

import { components } from 'tg.service/apiSchema.generated';
import { getLanguagesContent } from './getLanguagesContent';

type LanguageModel = components['schemas']['LanguageModel'];

type Props = {
  anchorEl: MenuProps['anchorEl'];
  onClose: () => void;
  languages?: LanguageModel[];
  value?: string[];
  onChange: (languages: string[]) => void;
};

export const LanguagesMenu: React.FC<React.PropsWithChildren<Props>> = ({
  anchorEl,
  onClose,
  languages,
  value,
  onChange,
}) => {
  return (
    <Menu
      anchorEl={anchorEl}
      open={Boolean(anchorEl)}
      onClose={onClose}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'left',
      }}
    >
      {getLanguagesContent({
        languages: languages || [],
        value: value || [],
        onChange,
      })}
    </Menu>
  );
};
